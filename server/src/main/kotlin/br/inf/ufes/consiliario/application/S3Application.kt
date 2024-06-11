package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.config.AWSConfig
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.InputStream

@Service
class S3Application(
    private val awsConfig: AWSConfig
) {
    private val bucketName = "bucket-ufes-consiliario"

    fun saveObject(resourceId: String, file: InputStream): String {
        val request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(resourceId)
            .build()

        val response = awsConfig.S3Client().putObject(request, RequestBody.fromBytes(file.readBytes()))

        return "https://$bucketName.s3.${Region.SA_EAST_1.id()}.amazonaws.com/$resourceId"
    }

    fun deleteObject(resourceId: String) {
        val listRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(resourceId)
            .build()

        val listResponse = awsConfig.S3Client().getObject(listRequest)

        if (listResponse.response().sdkHttpResponse().isSuccessful) {
            val deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(resourceId)
                .build()

            val response = awsConfig.S3Client().deleteObject(deleteRequest)

            response.deleteMarker()
        }
    }
}