# Bajaj Finserv Java Assignment – PES1UG22CS688

### 👤 Student Details
- **Name:** Vignesh
- **Reg. No:** PES1UG22CS688
- **Email:** pes1202203613@pesu.pes.edu

---

## 🧠 Problem Statement

You are required to calculate the number of employees who are younger than each employee, grouped by their respective departments.

### **Tables**
1. **DEPARTMENT**
    - DEPARTMENT_ID (Primary Key)
    - DEPARTMENT_NAME
2. **EMPLOYEE**
    - EMP_ID (Primary Key)
    - FIRST_NAME
    - LAST_NAME
    - DOB (Date of Birth)
    - GENDER
    - DEPARTMENT (Foreign Key referencing DEPARTMENT_ID)
3. **PAYMENTS**
    - PAYMENT_ID (Primary Key)
    - EMP_ID (Foreign Key referencing EMP_ID)
    - AMOUNT
    - PAYMENT_TIME (Date and time of transaction)

---

## ✅ Expected Output
| Column | Description |
|---------|--------------|
| EMP_ID | Employee ID |
| FIRST_NAME | First Name |
| LAST_NAME | Last Name |
| DEPARTMENT_NAME | Department Name |
| YOUNGER_EMPLOYEES_COUNT | No. of employees younger than this employee in the same department |

---

## 💡 SQL Query Used

```sql
SELECT 
    e1.EMP_ID,
    e1.FIRST_NAME,
    e1.LAST_NAME,
    d.DEPARTMENT_NAME,
    COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT
FROM 
    EMPLOYEE e1
JOIN 
    DEPARTMENT d 
    ON e1.DEPARTMENT = d.DEPARTMENT_ID
LEFT JOIN 
    EMPLOYEE e2 
    ON e1.DEPARTMENT = e2.DEPARTMENT 
    AND e2.DOB > e1.DOB
GROUP BY 
    e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
ORDER BY 
    e1.EMP_ID DESC;
    
---   
## ⚙️ Tech Stack

* **Java 21**
* **Spring Boot 3.3.5**
* **Maven Build Tool**
* **RestTemplate for API Calls**

---

## 🚀 How to Run

1. Clone the repo:

   ```bash
   git clone https://github.com/<your-username>/PES1UG22CS688_VIGNESH_JAVA_ASSIGNMENT.git
   ```
2. Navigate to project folder:

   ```bash
   cd PES1UG22CS688_VIGNESH_JAVA_ASSIGNMENT
   ```
3. Build:

   ```bash
   mvn clean package
   ```
4. Run:

   ```bash
   java -jar target/bajaj-1.0.0.jar
   ```

---

## 🏁 Output

```
🚀 Calling webhook generation endpoint...
✅ Webhook generated successfully!
🚀 Submitting SQL query to: https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
✅ SQL Query submitted successfully!
Server Response: {"success":true,"message":"Webhook processed successfully"}
```

