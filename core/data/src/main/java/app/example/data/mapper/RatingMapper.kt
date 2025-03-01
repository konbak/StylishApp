package app.example.data.mapper

import app.example.domain.model.RatingDomain
import app.example.network.model.response.Rating

class RatingMapper {
    fun mapToDomain(rating: Rating): RatingDomain {
        return RatingDomain(
            rate = rating.rate,
            count = rating.count
        )
    }
}