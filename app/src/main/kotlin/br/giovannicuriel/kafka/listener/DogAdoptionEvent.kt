package br.giovannicuriel.kafka.listener

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse
import br.giovannicuriel.kafka.DogAdoptionEvent

class DogAdoptionEventConfig {
    companion object {
        const val topicName = "br.giovannicuriel.kafka.dog_adoption_event"
        const val schemaFilename =
            "src/main/avro/br.giovannicuriel.kafka.dog_adoption_event-value.avsc"
    }
}

@RestController
class DogAdoptionEventController(
    private val producer: DogAdoptionEventProducer,
    private val mapper: ObjectMapper
){
    @PostMapping("/DogAdoptionEvent")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDogAdoptionEvent(@RequestBody request: DogAdoptionEvent) {
        producer.send(request)
    }

    @GetMapping("/DogAdoptionEvent/sample")
    fun getDogAdoptionEventSample(response: HttpServletResponse): String {
        val obj = SampleDataGenerator.generateRandomDataFromSchema(DogAdoptionEventConfig.schemaFilename)
        response.addHeader("Content-Type", "application/json")
        return obj
    }
}


@Service
class DogAdoptionEventProducer(
    private val template: KafkaTemplate<String, DogAdoptionEvent>,
){
    fun send(request: DogAdoptionEvent) {
        println("[producer] Sending event: ${request}")
        template.send(DogAdoptionEventConfig.topicName, request.payload.id.toString(), request)
    }
}

@Component
class DogAdoptionEventControllerListener(
) {
   @KafkaListener(topics = [DogAdoptionEventConfig.topicName],
       autoStartup = "true",
       groupId = "kafka-avro-validator-consumer"
   )
   fun processPaidInstallment(@Payload data: DogAdoptionEvent) {
       println("New event from topic ${DogAdoptionEventConfig.topicName}: $data")
   }
}
