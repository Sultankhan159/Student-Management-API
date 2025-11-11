# 🎓 Student Management API

A RESTful API for managing student information, courses, and academic records. This API provides comprehensive endpoints for creating, retrieving, updating, and deleting student data with role-based access control.

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [API Documentation](#api-documentation)
- [Authentication](#authentication)
- [Endpoints](#endpoints)
- [Database Schema](#database-schema)
- [Usage Examples](#usage-examples)
- [Error Handling](#error-handling)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### Core Functionality
- **Student Management**: Complete CRUD operations for student records
- **Course Management**: Create and manage courses
- **Enrollment System**: Enroll students in courses
- **Grade Management**: Track and update student grades
- **Authentication & Authorization**: JWT-based secure authentication with role-based access
- **Search & Filter**: Advanced search capabilities for students and courses
- **Performance Tracking**: Monitor student academic performance
- **Profile Management**: Students can update their own information

### Role-Based Access
- **Admin/Teacher**: Full access to all operations
  - Add, update, delete students
  - Create and manage courses
  - Enroll students in courses
  - Add and update grades
  - View all student records
  
- **Student**: Limited access
  - View own profile
  - Update personal information
  - View enrolled courses
  - Check grades and performance

## 🛠️ Tech Stack

**Backend Framework:**
- Node.js with Express.js / Python Flask / Python Django / Java Spring Boot

**Database:**
- MongoDB / MySQL / PostgreSQL

**Authentication:**
- JWT (JSON Web Tokens)

**Additional Libraries:**
- bcrypt (password hashing)
- cors (Cross-Origin Resource Sharing)
- dotenv (environment variables)
- validator (input validation)

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Node.js** (v14 or higher) / **Python** (3.8+) / **Java** (11+)
- **npm** / **pip** / **maven**
- **MongoDB** / **MySQL** / **PostgreSQL**
- **Git**
- **Postman** (optional, for testing API endpoints)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Sultankhan159/Student-Management-API.git
cd Student-Management-API
```

### 2. Install Dependencies

**For Node.js/Express:**
```bash
npm install
```

**For Python/Flask or Django:**
```bash
pip install -r requirements.txt
```

**For Java/Spring Boot:**
```bash
mvn install
```

### 3. Configure Environment Variables

Create a `.env` file in the root directory:

```env
# Server Configuration
PORT=5000
NODE_ENV=development

# Database Configuration
DB_HOST=localhost
DB_PORT=27017
DB_NAME=student_management
DB_USER=your_username
DB_PASSWORD=your_password

# MongoDB Connection String (if using MongoDB)
MONGODB_URI=mongodb://localhost:27017/student_management

# MySQL Connection String (if using MySQL)
DATABASE_URL=mysql://username:password@localhost:3306/student_management

# JWT Configuration
JWT_SECRET=your_jwt_secret_key_here
JWT_EXPIRE=7d

# Application Settings
API_VERSION=v1
```

### 4. Initialize Database

**For SQL databases:**
```bash
# Run migrations
npm run migrate
# or
python manage.py migrate
```

**For MongoDB:**
The database will be created automatically on first run.

### 5. Seed Database (Optional)

```bash
npm run seed
# or
python seed.py
```

### 6. Start the Server

**Development Mode:**
```bash
npm run dev
# or
python app.py
# or
mvn spring-boot:run
```

**Production Mode:**
```bash
npm start
```

The API will be available at `http://localhost:5000`

## 📚 API Documentation

### Base URL
```
http://localhost:5000/api/v1
```

### API Response Format

**Success Response:**
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { }
}
```

**Error Response:**
```json
{
  "success": false,
  "message": "Error message",
  "error": "Detailed error information"
}
```

## 🔐 Authentication

This API uses JWT (JSON Web Tokens) for authentication.

### Register User

**Endpoint:** `POST /api/v1/auth/register`

**Request Body:**
```json
{
  "email": "student@example.com",
  "password": "securePassword123",
  "firstName": "John",
  "lastName": "Doe",
  "role": "student"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Registration successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": "12345",
      "email": "student@example.com",
      "firstName": "John",
      "lastName": "Doe",
      "role": "student"
    }
  }
}
```

### Login

**Endpoint:** `POST /api/v1/auth/login`

**Request Body:**
```json
{
  "email": "student@example.com",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": "12345",
      "email": "student@example.com",
      "role": "student"
    }
  }
}
```

### Using the Token

Include the JWT token in the Authorization header for protected routes:

```
Authorization: Bearer <your_jwt_token>
```

## 🔌 Endpoints

### Students

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/v1/students` | Get all students | Admin/Teacher |
| GET | `/api/v1/students/:id` | Get student by ID | Admin/Teacher/Student (own) |
| POST | `/api/v1/students` | Create new student | Admin/Teacher |
| PUT | `/api/v1/students/:id` | Update student | Admin/Teacher/Student (own) |
| DELETE | `/api/v1/students/:id` | Delete student | Admin/Teacher |
| GET | `/api/v1/students/:id/courses` | Get student's courses | Admin/Teacher/Student (own) |
| GET | `/api/v1/students/:id/grades` | Get student's grades | Admin/Teacher/Student (own) |

### Courses

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/v1/courses` | Get all courses | All authenticated users |
| GET | `/api/v1/courses/:id` | Get course by ID | All authenticated users |
| POST | `/api/v1/courses` | Create new course | Admin/Teacher |
| PUT | `/api/v1/courses/:id` | Update course | Admin/Teacher |
| DELETE | `/api/v1/courses/:id` | Delete course | Admin/Teacher |
| POST | `/api/v1/courses/:id/enroll` | Enroll student in course | Admin/Teacher |
| DELETE | `/api/v1/courses/:id/unenroll` | Unenroll student | Admin/Teacher |

### Grades

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/v1/grades` | Add grade | Admin/Teacher |
| PUT | `/api/v1/grades/:id` | Update grade | Admin/Teacher |
| DELETE | `/api/v1/grades/:id` | Delete grade | Admin/Teacher |
| GET | `/api/v1/grades/student/:studentId` | Get student grades | Admin/Teacher/Student (own) |
| GET | `/api/v1/grades/course/:courseId` | Get course grades | Admin/Teacher |

## 💾 Database Schema

### Students Collection/Table

```javascript
{
  id: "unique_identifier",
  firstName: "string",
  lastName: "string",
  email: "string (unique)",
  password: "string (hashed)",
  dateOfBirth: "date",
  address: "string",
  phoneNumber: "string",
  enrollmentDate: "date",
  studentId: "string (unique, auto-generated)",
  role: "string (default: 'student')",
  status: "string (active/inactive)",
  createdAt: "timestamp",
  updatedAt: "timestamp"
}
```

### Courses Collection/Table

```javascript
{
  id: "unique_identifier",
  courseCode: "string (unique)",
  courseName: "string",
  description: "string",
  credits: "number",
  instructor: "string",
  semester: "string",
  year: "number",
  maxStudents: "number",
  enrolledStudents: ["array of student IDs"],
  createdAt: "timestamp",
  updatedAt: "timestamp"
}
```

### Grades Collection/Table

```javascript
{
  id: "unique_identifier",
  studentId: "reference to Student",
  courseId: "reference to Course",
  grade: "string or number",
  semester: "string",
  year: "number",
  remarks: "string",
  createdAt: "timestamp",
  updatedAt: "timestamp"
}
```

### Enrollments Collection/Table

```javascript
{
  id: "unique_identifier",
  studentId: "reference to Student",
  courseId: "reference to Course",
  enrollmentDate: "date",
  status: "string (enrolled/completed/dropped)",
  createdAt: "timestamp"
}
```

## 💡 Usage Examples

### Create a Student (Admin/Teacher)

```bash
curl -X POST http://localhost:5000/api/v1/students \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com",
    "dateOfBirth": "2000-05-15",
    "phoneNumber": "+1234567890"
  }'
```

### Get All Students with Pagination

```bash
curl -X GET "http://localhost:5000/api/v1/students?page=1&limit=10" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Enroll Student in Course

```bash
curl -X POST http://localhost:5000/api/v1/courses/COURSE_ID/enroll \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "studentId": "STUDENT_ID"
  }'
```

### Add Grade

```bash
curl -X POST http://localhost:5000/api/v1/grades \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "studentId": "STUDENT_ID",
    "courseId": "COURSE_ID",
    "grade": "A",
    "semester": "Fall",
    "year": 2024
  }'
```

### Search Students

```bash
curl -X GET "http://localhost:5000/api/v1/students?search=john&status=active" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## ⚠️ Error Handling

The API uses standard HTTP status codes:

| Status Code | Description |
|-------------|-------------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 409 | Conflict |
| 500 | Internal Server Error |

### Common Error Responses

**Validation Error:**
```json
{
  "success": false,
  "message": "Validation failed",
  "errors": [
    {
      "field": "email",
      "message": "Invalid email format"
    }
  ]
}
```

**Authentication Error:**
```json
{
  "success": false,
  "message": "Authentication failed",
  "error": "Invalid or expired token"
}
```

**Not Found Error:**
```json
{
  "success": false,
  "message": "Student not found",
  "error": "No student exists with ID: 12345"
}
```

## 🧪 Testing

### Run Tests

```bash
# Run all tests
npm test

# Run tests with coverage
npm run test:coverage

# Run specific test file
npm test tests/student.test.js
```

### Test with Postman

1. Import the Postman collection from `/postman/Student-Management-API.postman_collection.json`
2. Set up environment variables
3. Run the collection

## 📊 Project Structure

```
Student-Management-API/
│
├── src/
│   ├── controllers/        # Request handlers
│   │   ├── authController.js
│   │   ├── studentController.js
│   │   ├── courseController.js
│   │   └── gradeController.js
│   │
│   ├── models/            # Database models
│   │   ├── Student.js
│   │   ├── Course.js
│   │   ├── Grade.js
│   │   └── User.js
│   │
│   ├── routes/            # API routes
│   │   ├── authRoutes.js
│   │   ├── studentRoutes.js
│   │   ├── courseRoutes.js
│   │   └── gradeRoutes.js
│   │
│   ├── middleware/        # Custom middleware
│   │   ├── auth.js
│   │   ├── validator.js
│   │   └── errorHandler.js
│   │
│   ├── utils/             # Utility functions
│   │   ├── generateToken.js
│   │   └── sendEmail.js
│   │
│   └── config/            # Configuration files
│       ├── database.js
│       └── constants.js
│
├── tests/                 # Test files
│   ├── student.test.js
│   ├── course.test.js
│   └── auth.test.js
│
├── postman/              # Postman collection
├── .env.example          # Environment variables template
├── .gitignore
├── package.json
├── README.md
└── server.js            # Entry point
```

## 🔒 Security Features

- **Password Hashing**: Bcrypt with salt rounds
- **JWT Authentication**: Secure token-based authentication
- **Input Validation**: Comprehensive request validation
- **SQL Injection Prevention**: Parameterized queries
- **XSS Protection**: Input sanitization
- **CORS Configuration**: Controlled cross-origin access
- **Rate Limiting**: Prevent brute force attacks
- **Helmet.js**: Security headers

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards

- Follow ESLint configuration
- Write meaningful commit messages
- Add tests for new features
- Update documentation

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Sultan Khan**
- GitHub: [@Sultankhan159](https://github.com/Sultankhan159)
- Email: sultan.khan@example.com

## 🙏 Acknowledgments

- Express.js documentation
- JWT best practices
- MongoDB/MySQL documentation
- RESTful API design principles

## 📞 Support

If you encounter any issues or have questions:
- Open an issue on GitHub
- Contact: sultan.khan@example.com
- Check existing issues before creating new ones

## 🗺️ Roadmap

- [ ] Add email notifications for enrollment
- [ ] Implement attendance tracking
- [ ] Add file upload for student documents
- [ ] Create admin dashboard
- [ ] Add export functionality (PDF/Excel)
- [ ] Implement real-time notifications
- [ ] Add support for multiple institutions
- [ ] Create mobile app integration
- [ ] Add analytics and reporting features

---

**Made with ❤️ by Sultan Khan**
