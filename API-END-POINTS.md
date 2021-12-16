Description:  
    On Registering of a user a unique user-id is generated and all its datas are saved in the database , password is saved in encrypted form. On every api hit there are multiple checks to verify the authencity of the user.
    
Technology Used:
    Java 11, Hibernate, Postgres DB


End Points:

1). Sign Up(POST): http://localhost:6002/user/register
    
    Request Body : 
    {
      "emailId" : "mind@gmail.com",
      "password" : "test@4879"
    }
    
    Response:
    {
      "Success": "User registered successfully, please login"
    }

2). Login(GET): http://localhost:6002/user/login?email=mind@gmail.com&password=test@4879

    Response:
    {
      "Success": "Logged In successfully"
    }

3). Password Change(PUT): http://localhost:6002/user/update-password?email=mind@gmail.com&oldPass=test@4879&newPass=Ashish@123458
    
    Response:
    {
      "Success": "Password updated successfully"
    }

4). Get All Users(GET): http://localhost:6002/user/all-users?pageNo=0&noOfItems=3

    Response:
    [
        {
            "user-id": "75f8fb51-0bd5-4e07-b4b8-cac3896720ed",
            "email": "bosss@gmail.com"
        },
        {
            "user-id": "9e748e73-5f0f-400d-9d20-b4e1900af23b",
            "email": "test@gmail.com"
        },
        {
            "user-id": "bdace584-fc05-45e9-b560-1e677f75773c",
            "email": "ashish@gmail.com"
        }
    ]

5). Delete User(DELETE): http://localhost:6002/user/delete-user?email=mind@gmail.com&password=test@4879 

    Response:
    {
      "Success": "User deleted successfully"
    }
