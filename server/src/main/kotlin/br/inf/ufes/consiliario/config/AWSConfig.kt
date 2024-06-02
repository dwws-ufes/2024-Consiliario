package br.inf.ufes.consiliario.config

import software.amazon.awssdk.auth.credentials.AwsCredentials
import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.InputStream
import java.util.*

@Service
class AWSConfig {
    val bucketName = "bucket-ufes-consiliario"

    private fun credentials(): AwsCredentials {
        return AwsBasicCredentials.create(
            "",
            ""
        )
    }

    private fun S3Client(): S3Client {
        return S3Client.builder()
            .region(Region.SA_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(credentials()))
            .build()
    }

    fun listObjects() {
        val request = ListObjectsV2Request.builder()
            .bucket(bucketName)
            .build()

        val response = S3Client().listObjectsV2(request)
    }

    fun saveObject(resourceId: String, file: InputStream): String {
        val request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(resourceId)
            .build()

        val response = S3Client().putObject(request, RequestBody.fromBytes(file.readBytes()))

        return "https://$bucketName.s3.${Region.SA_EAST_1.id()}.amazonaws.com/$resourceId"
    }
}