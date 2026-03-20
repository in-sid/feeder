package com.nononsenseapps.feeder.db.room

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AllowlistDao {
    @Query(
        """
            INSERT INTO allowlist (id, glob_pattern)
            VALUES (null, '*' || :pattern || '*')
        """,
    )
    suspend fun insertSafely(pattern: String)

    @Query(
        """
            DELETE FROM allowlist
            WHERE glob_pattern = ('*' || :pattern || '*')
        """,
    )
    suspend fun deletePattern(pattern: String)

    @Query(
        """
            SELECT glob_pattern
            FROM allowlist
            ORDER BY glob_pattern
        """,
    )
    fun getGlobPatterns(): Flow<List<String>>
}
