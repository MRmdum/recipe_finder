package com.avigationaled

//UserDB
import androidx.room.Entity
import androidx.room.PrimaryKey
//UserDAO
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
//AppDatabase
import androidx.room.Database
import androidx.room.RoomDatabase
//UserRepository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Entity(tableName = "user_table")
class UserDB (
    @PrimaryKey val id: Int,
    val username: String,
    val password: String
)


@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserDB)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM user_table WHERE id = :userId")
    fun getUserById(userId: Int): List<UserDB>?

    @Query("SELECT * FROM user_table WHERE username = :username")
    fun getUserName(username: String): UserDB?

    @Query("INSERT INTO user_table (username,password) VALUES (:username,:password)")
    fun  createUser(username:String, password:String): UserDB?
}

@Database(entities = [UserDB::class,Meal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun mealDao(): MealDao

}


class UserRepository(context: Context) {

    private val database: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()

    private val userDao: UserDao = database.userDao()
    private val mealDao: MealDao = database.mealDao()

    /// User Table ///
    suspend fun insertUser(user: UserDB) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }
    suspend fun getUserById(userId: Int): UserDB? {
        return withContext(Dispatchers.IO) {
            val user = userDao.getUserById(userId)
            if(user != null){
                user[0]
            }
            else null
        }
    }
    suspend fun getUserName(userId: Int): UserDB? {
        return withContext(Dispatchers.IO) {
            val user = userDao.getUserById(userId)
            if(user != null){
                user[0]
            }
            else null
        }
    }

    /// Meal Table ///
    suspend fun getMealById(idMeal: Int): Meal? {
        return withContext(Dispatchers.IO) {
            mealDao.getMealById(idMeal)
        }
    }
    suspend fun getMealByName(strMeal: String): List<Meal>? {
        return withContext(Dispatchers.IO) {
            mealDao.getMealByName(strMeal)
        }
    }
    suspend fun getAllMeal(): List<Meal>? {
        return withContext(Dispatchers.IO) {
            mealDao.getAllMeal()
        }
    }
    suspend fun insertMeal(meal: Meal) {
        withContext(Dispatchers.IO) {
            mealDao.insertMeal(meal)
        }
    }
}