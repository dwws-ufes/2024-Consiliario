package br.inf.ufes.consiliario.config

import software.amazon.awssdk.auth.credentials.AwsCredentials
import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@Service
class AWSConfig {
    private fun credentials(): AwsCredentials {
        return AwsBasicCredentials.create(
            "",
            ""
        )
    }

    fun S3Client(): S3Client {
        return S3Client.builder()
            .region(Region.SA_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(credentials()))
            .build()
    }
}