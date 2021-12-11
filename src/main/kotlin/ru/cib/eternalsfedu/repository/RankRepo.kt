package ru.cib.eternalsfedu.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ru.cib.eternalsfedu.domain.Rank

interface RankRepo : PagingAndSortingRepository<Rank, Long> {
}