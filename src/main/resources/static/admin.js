const url = 'http://localhost:8080/api/admin/user';
const URL = 'http://localhost:8080/api/admin';
function getUsers() {
    fetch(URL)
        .then(function (res) {
            return res.json()
        })
        .then(user => {
            let roles =[];
            for (const role of user.roles) {
                roles.push(role.join(' '))
            }
            let response = '';
            response +=
                `<tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.username}</td>
                    <td>${roles}</td>
                    <td>
                        <button class="btn-info" type="button" data-toggle="modal"
                        data-target="#editModal" onclick="editModal(${user.id})">Edit</button>
                    </td>
                    <td>
                        <button class="btn-danger" type="button" data-toggle="modal"
                        data-target="#deleteModal" onclick="deleteModal(${user.id})">Delete</button>
                    </td>
                </tr>`
            document.getElementById('adminId').innerHTML = response;
        })


}

getUsers();

document.addEventListener('DOMContentLoaded', function getAdminInfo() {
    fetch(url)
        .then(res => res.json())
        .then(user => {
            let roles =[];
            for (const role of user.roles) {
                roles.push(role.join(' '))
            }
            let info = '';
            info +=
                `<tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.username}</td>
                    <td>${roles}</td>
                </tr>`
            document.getElementById('info').innerHTML = info;
        })

});

document.getElementById("addUser").addEventListener("submit", addUser);

function addUser() {
    let name = document.getElementById('inputName').value;
    let lastName = document.getElementById('inputSurname').value;
    let age = document.getElementById('inputAge').value;
    let username = document.getElementById('inputEmail').value;
    let password = document.getElementById('inputPassword').value;
    let role = document.getElementById('inputRole').value;

    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            'name': name,
            'lastName': lastName,
            'age': age,
            'email': username,
            'password': password,
            'role': role
        })
    }).then(response => {
        if (response.ok) {
            getUsers();
        } else {
            alert('Error, ${response.status}')
        }
    })

}

function editModal(id) {
    fetch(URL + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (response) {
        return response.json();
    }).then(user => {
        document.getElementById('ID').value = user.id;
        document.getElementById('newName').value = user.firstName;
        document.getElementById('newSurname').value = user.lastName;
        document.getElementById('newAge').value = user.age;
        document.getElementById('newEmail').value = user.username;
        document.getElementById('newPassword').value = user.password;
        document.getElementById('newRole').value = user.roles;
    })

}

function editUser() {
    let name = document.getElementById('newName').value
    let lastName = document.getElementById('newSurname').value
    let age = document.getElementById('newAge').value
    let username = document.getElementById('newEmail').value
    let password = document.getElementById('newPassword').value
    let role = document.getElementById('newRole').value

    fetch(URL, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            'name': name,
            'lastName': lastName,
            'age': age,
            'email': username,
            'password': password,
            'role': role
        })
    }).then(() => {
        $('#editModal').hide();
        getUsers();

    })
}

function deleteModal(id) {
    fetch(URL + '/' + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (response) {
        return response.json();
    }).then(user => {
        document.getElementById('inputID').value = user.id;
        document.getElementById('firstName').value = user.firstName;
        document.getElementById('lastName').value = user.lastName;
        document.getElementById('Age').value = user.age;
        document.getElementById('Email').value = user.username;
        document.getElementById('Role').value = user.roles;
    })

}

function deleteUser() {
    let id = document.getElementById('inputID').value
    let name = document.getElementById('firstName').value
    let lastName = document.getElementById('lastName').value
    let age = document.getElementById('Age').value
    let username = document.getElementById('Email').value
    let role = document.getElementById('Role').value

    fetch(URL + '/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }

    }).then(() => {
        $('#deleteModal').hide();
        getUsers();

    })
}