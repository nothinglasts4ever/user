package com.company.user.service

import com.company.user.client.AgifyClient
import com.company.user.client.api.AgifyInfo
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AgifyService(private val client: AgifyClient) {

    fun getUserAgeOrNull(name: String): Int? {
        val agifyInfo = try {
            client.getAgifyInfo(name)
        } catch (e: FeignException) {
            return null
        }
        return agifyInfo.age
    }

    fun getUserAges(names: List<String>): Map<String, Int?> {
        val agifyInfoList = try {
            client.getBatchAgifyInfo(names.toSet())
        } catch (e: FeignException) {
            return emptyMap()
        }
        logger.info(agifyInfoList.toString())
        return agifyInfoList.associateBy(AgifyInfo::name, AgifyInfo::age)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

}
