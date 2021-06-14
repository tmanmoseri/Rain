package com.rain.shortUrl;

import com.google.common.hash.Hashing;
import io.netty.util.HashingStrategy;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequestMapping
@RestController
public class ShortUrlResource {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id) {

        String url = redisTemplate.opsForValue().get(id);
        System.out.println("Url retrieved: "+ url);

        if (url == null){
            throw new RuntimeException(" There is no URL for : " + id);
        }
        return url;
    }
    @PostMapping
    public String  create(@RequestBody String url){
        UrlValidator urlValidator= new UrlValidator(
                new String[]{"http","https"} // Validate only http and https urls
        );
      if ( urlValidator.isValid(url)){
          //creating unique identifier using murmurhash provided by google.guava
          String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();

          System.out.println("Url Id generated: "+ id);
          redisTemplate.opsForValue().set(id, url);
          return id;
      }
      throw new RuntimeException("Url invalid: " + url);
    }
}
