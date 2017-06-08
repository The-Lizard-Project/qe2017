package pl.lizardproject.qe2017.session

import pl.lizardproject.qe2017.model.User

class UserSession {
    private var user: User? = null

    fun start(user: User) {
        this.user = user
    }

    fun finish() {
        user = null
    }
}