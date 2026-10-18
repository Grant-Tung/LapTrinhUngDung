package com.example.lab2_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etId, etFullName;
    private CheckBox cbManager;
    private Button btnAdd;
    private RecyclerView rvEmployees;

    private List<Employee> employeeList;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ các View từ file activity_main.xml
        etId = findViewById(R.id.et_id);
        etFullName = findViewById(R.id.et_fullname);
        cbManager = findViewById(R.id.cb_manager);
        btnAdd = findViewById(R.id.btn_add);
        rvEmployees = findViewById(R.id.rv_employees);

        // 2. Khởi tạo danh sách dữ liệu và Adapter
        employeeList = new ArrayList<>();
        adapter = new EmployeeAdapter(this, employeeList);

        // 3. Thiết lập LayoutManager (cuộn dọc) và gắn Adapter cho RecyclerView
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        rvEmployees.setAdapter(adapter);

        // 4. Xử lý sự kiện nhấn nút ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEmployee();
            }
        });
    }

    private void addNewEmployee() {
        String id = etId.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        boolean isManager = cbManager.isChecked();

        if (id.isEmpty() || fullName.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ ID và Full Name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng Employee mới và đưa vào danh sách
        Employee newEmployee = new Employee(id, fullName, isManager);
        employeeList.add(newEmployee);

        // Cập nhật giao diện RecyclerView tại vị trí vừa thêm mới
        adapter.notifyItemInserted(employeeList.size() - 1);

        // Xóa trắng ô nhập liệu sau khi thêm để tiện nhập người tiếp theo
        etId.setText("");
        etFullName.setText("");
        cbManager.setChecked(false);
        etId.requestFocus();
    }
}