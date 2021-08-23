package geekbrains.slava_5655380.domain.models.repositories.github.repository

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import geekbrains.slava_5655380.domain.models.repositories.github.user.RoomGithubUser

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey var id: String,
    var name: String,
    var description: String,
    var userId: String
)