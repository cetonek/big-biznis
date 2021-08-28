package com.github.cetonek.bigbiznis

import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class FileReader {

    val BASE_PATH = "/com/github/cetonek/bigbiznis/"
    val MOCK_PATH = "${BASE_PATH}mock/"

    fun readAsString(path: String) : String {
        return IOUtils.resourceToString(path, StandardCharsets.UTF_8)
    }

    fun readMockAsString(relativePath: String) : String {
        return readAsString("${MOCK_PATH}/${relativePath}");
    }

}