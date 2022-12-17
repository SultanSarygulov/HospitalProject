package com.example.hospitalproject.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StaffDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addStaff(Staff staff);

    @Delete
    void deleteStaff(Staff staff);

    @Query("SELECT * FROM staff_table WHERE position = 'Doctor'")
    List<Staff> getDoctors();

    @Query("SELECT * FROM staff_table WHERE position = 'Nurse'")
    List<Staff> getNurses();

    @Query("SELECT * FROM  staff_table ORDER BY salary DESC LIMIT 1")
    Staff getMostPaid();

    @Query("SELECT * FROM  staff_table ORDER BY salary ASC LIMIT 1")
    Staff getLeastPaid();

}
