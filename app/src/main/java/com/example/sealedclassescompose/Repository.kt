package com.example.sealedclassescompose

import android.util.Log
import kotlinx.coroutines.delay
import kotlin.random.Random

class Repository {

    private val TAG = "TAGOWE"

    suspend fun getData(): Resource<List<Person>> {
        return try {
            delay(5000)
            if (Random.nextInt(2) == 0) {
                Log.d(TAG, "getData: Exception")
                throw Exception()
            }

            Log.d(TAG, "getData: Sucess!")
            Resource.Success(
                listOf(
                    Person(
                        name = "Chris P.Bacom",
                        gender = Person.Gender.Male
                    ),
                    Person(
                        name = "Chris Papina",
                        gender = Person.Gender.Female
                    ),
                    Person(
                        name = "Mamimka Sutiti",
                        gender = Person.Gender.Male
                    )
                )
            )
        } catch (e: Exception) {
            Resource.Error("Network error")
        }

    }

}