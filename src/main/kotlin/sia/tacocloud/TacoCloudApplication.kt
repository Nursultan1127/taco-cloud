package sia.tacocloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.stream.Collectors

import java.util.stream.IntStream


@SpringBootApplication
class TacoCloudApplication

fun main(args: Array<String>) {
    runApplication<TacoCloudApplication>(*args)
}