package br.inf.ufes.consiliario.config

import br.inf.ufes.consiliario.entity.RecommendationType
import org.springframework.core.convert.converter.Converter

class EnumConverter : Converter<RecommendationType, RecommendationType> {
    override fun convert(recommendation: RecommendationType): RecommendationType {
        return recommendation
    }
}