package vcnc.braze

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.File
import kotlin.math.max
import kotlin.math.min
import kotlin.system.measureTimeMillis

@Component
class Worker (
    val properties: BrazeConfigProperties,
    val client: BrazeClient,
    val converter: Converter
){
    private val MAX_OBJECT_COUNT = 75
    private val sleepTimeMills = max(2, (60 * 1000 / properties.transferRate).toLong())
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun  handle() {
        var fileCount = 0

        val dataFiles = createTargetFileList(properties.dataDir, properties.listFilePath)
        dataFiles
            .map { File(it) }
            .filter { it.exists() }
            .forEach { dataFile ->
                process(dataFile)
                fileCount++
            }

        println("Total: ${fileCount} files")
    }

    private fun process(dataFile: File) {
        var lineNumber = 0
        var errCount = 0
        val inputStream = dataFile.inputStream()

        val elapsed = measureTimeMillis {
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach { jsonStr ->
                    lineNumber++
                    val profile = try {
                        client.jsonToProfile(jsonStr)
                    } catch (e: Exception) {
                        errCount++
                        return@forEach
                    }
                    val externalId = profile.external_id
                    try {
                        if (properties.uploadAttrs) {
                            sendAttributes(profile.custom_attributes, externalId)
                        }
                        if (properties.uploadEvents) {
                            sendEvents(profile.custom_events, externalId)
                        }
                    } catch (e: Exception) {
                        logger.error("fatal error at: ${dataFile.path} #$lineNumber")
                        throw e
                    }
                }
            }
        }

        val seconds = elapsed.toDouble()/1000
        println("finished: ${dataFile.name}, elapsed: $seconds sec, users: $lineNumber, errors: $errCount")
    }

    private fun track(params: List<TrackParams>) {
        client.track(params)
        Thread.sleep(sleepTimeMills)
    }

    private fun sendAttributes(attrs: UserAttr, id: String) {
        val trackAttributeParams = converter.convertUserAttrToTrackParams(attrs, id)
        track(listOf(trackAttributeParams))
    }

    private fun sendEvents(events: List<UserEvent>, id: String) {
        var sum = 0
        var subList = emptyList<UserEvent>()
        val list = events.sortedBy { it.count }

        for (i in list.indices) {
            val count = min(MAX_OBJECT_COUNT, list[i].count)

            if ((sum + count) <= MAX_OBJECT_COUNT) {
                subList += list[i]
                sum += count
            } else {
                val trackParams = converter.convertUserEventListTolTrackParamsList(subList, id)
                track(trackParams)

                subList = listOf(list[i])
                sum = count
            }
        }

        if (sum > 0) {
            val trackParams = converter.convertUserEventListTolTrackParamsList(subList, id)
            track(trackParams)
        }
    }

    private fun createTargetFileList(targetDir: String, listFilePath: String): List<String> {
        val files = File(listFilePath)

        return  files.readLines()
            .filter { it.isNotEmpty() }
            .filter { it[0] != '#' }
            .map { it.trim() }
            .map { fileName -> "$targetDir/$fileName" }
    }
}