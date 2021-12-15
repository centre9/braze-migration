package vcnc.braze

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BrazeMigrationApplication(
	private val worker: Worker
): CommandLineRunner {

	override fun run(vararg args: String?) {
		println("Application starts")

		worker.handle()

		println("Application ends")
	}
}

fun main(args: Array<String>) {
	runApplication<BrazeMigrationApplication>(*args).close()
}
