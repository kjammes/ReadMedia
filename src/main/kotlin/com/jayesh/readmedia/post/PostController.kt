package com.jayesh.readmedia.post

import com.jayesh.readmedia.exceptions.FetchFailureException
import com.jayesh.readmedia.utils.ConversionUtil
import com.jayesh.readmedia.utils.CustomResponse
import com.jayesh.readmedia.utils.validation.InputValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostController
@Autowired
constructor(
    private val postsService: PostsService,
    private val LOG: Logger = LoggerFactory.getLogger("PostController"),
    private val inputValidator: InputValidator = InputValidator(),
    private val conversionUtil: ConversionUtil = ConversionUtil()
) {
    @GetMapping("")
    fun getPosts(@RequestParam(defaultValue = "0") skip: Int,@RequestParam(defaultValue = "5") limit: Int) : ResponseEntity<CustomResponse<Page<Post>>> {
        var customResponse: CustomResponse<Page<Post>>
        var posts : Page<Post> = Page.empty()
        return try {
            LOG.info("Fetching all posts")
            posts = postsService.findAllPaginated(skip, limit)
            customResponse = CustomResponse("Successfully fetched data", posts)
            ResponseEntity(customResponse, HttpStatus.OK)
        } catch (e: FetchFailureException) {
            customResponse = CustomResponse(e.message ?: "Failed to fetch posts", posts)
            ResponseEntity(customResponse, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("")
    fun savePost(@RequestBody postDto: PostDto) : ResponseEntity<CustomResponse<Boolean>> {
        var customResponse: CustomResponse<Boolean>
        val validationResponse = inputValidator.isValidPostDto(postDto)
        if(validationResponse.isNotEmpty()) {
            customResponse = CustomResponse(validationResponse.toString(), false)
            ResponseEntity(customResponse, HttpStatus.BAD_REQUEST)
        }

        val post = conversionUtil.convertToPost(postDto)
        return try {
            LOG.info("Saving post")
            val result = postsService.savePost(post)
            customResponse = CustomResponse("Successfully saved the post!", result)
            ResponseEntity(customResponse, HttpStatus.CREATED)
        } catch (e : RuntimeException) {
            customResponse = CustomResponse(e.message ?: "Failed to save the post", false)
            ResponseEntity(customResponse, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable(value = "id") id: Long) : ResponseEntity<CustomResponse<String>> {
        val message = postsService.deletePost(id)
        return ResponseEntity(CustomResponse(message, ""), HttpStatus.OK)
    }
}