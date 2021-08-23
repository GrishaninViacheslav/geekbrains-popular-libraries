package geekbrains.slava_5655380.domain.models.repositories.github.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGithubUser(
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String
)