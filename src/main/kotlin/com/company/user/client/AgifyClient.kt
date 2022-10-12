package com.company.user.client

import com.company.user.client.api.AgifyInfo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "AgifyClient", url = "https://api.agify.io")
interface AgifyClient {

    @GetMapping
    fun getAgifyInfo(@RequestParam name: String): AgifyInfo

    @GetMapping
    fun getBatchAgifyInfo(@RequestParam(name = "name[]") names: Set<String>): List<AgifyInfo>

}
