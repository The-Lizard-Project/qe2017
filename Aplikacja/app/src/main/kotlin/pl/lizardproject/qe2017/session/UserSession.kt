package pl.lizardproject.qe2017.session

import pl.lizardproject.qe2017.model.User

class UserSession {
    var user: User? = null
        private set

    fun start(user: User) {
        this.user = user
    }

    fun end() {
        user = null
    }
}