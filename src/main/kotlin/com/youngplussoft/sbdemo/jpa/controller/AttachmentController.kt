package com.youngplussoft.sbdemo.jpa.controller

import com.youngplussoft.sbdemo.jpa.model.Attachment
import com.youngplussoft.sbdemo.jpa.repository.AttachmentRepository
import mu.KotlinLogging
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import io.ktor.client.engine.*
import javax.servlet.http.HttpServletResponse

val logger = KotlinLogging.logger {}

@RestController
class AttachmentController {
    private val logger = LoggerFactory.getLogger(AttachmentController::class.java)

    @Autowired
    var attachmentRepository: AttachmentRepository? = null

    @Value("\${spring.profiles.active}")
    var activeSpringProfile = "local"

    @PostMapping(produces = ["application/json;charset=UTF-8"], value = ["/attachment"])
    fun addAttachment(@RequestBody attachment: Attachment): Attachment? {
        var attachment = attachment
        return try {
            attachment = attachmentRepository!!.saveAndFlush(attachment)
            return attachment
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

}