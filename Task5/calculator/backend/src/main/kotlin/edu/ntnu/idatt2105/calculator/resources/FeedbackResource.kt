package edu.ntnu.idatt2105.calculator.resources

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/feedback")
@RestController
class FeedbackResource {

    @PostMapping
    fun postFeedbackFormData(): ResponseEntity<String>
        = ResponseEntity("OK", HttpStatus.valueOf(201))

}
