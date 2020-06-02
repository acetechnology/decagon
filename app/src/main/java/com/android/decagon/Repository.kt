package com.android.decagon

import com.android.decagon.Network.Client
import com.android.decagon.Network.Services

class Repository {

    private var client: Services = Client.getClient()

    // START article_users endpoints
    suspend fun getUsers(
        page: Int? = 0
    ) = client.getUsers(page)

}