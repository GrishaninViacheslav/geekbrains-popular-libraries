package geekbrains.slava_5655380.domain.models.githubusers.githubrepository

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val name: String? = null,
    @Expose val description: String? = null
) : Parcelable