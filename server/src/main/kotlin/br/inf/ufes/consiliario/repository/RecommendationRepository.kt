package br.inf.ufes.consiliario.repository

import br.inf.ufes.consiliario.entity.Recommendation
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface RecommendationRepository : CoroutineCrudRepository<Recommendation, UUID>