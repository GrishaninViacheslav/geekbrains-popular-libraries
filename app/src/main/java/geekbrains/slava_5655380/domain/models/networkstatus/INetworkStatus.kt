package geekbrains.slava_5655380.domain.models.networkstatus

import io.reactivex.Observable
import io.reactivex.Single

interface INetworkStatus{
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}