const userUrl = 'http://localhost:8080/api/user';

function getUserPage() {
    fetch(userUrl)
        .then(response => response.json())
        .then(user => {
            let res= '';
             res +=
                `<tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.username}</td>
                    <td>${user.roles.map(role => role.role.join(' '))}</td>
                </tr>`
            document.getElementById("userId").innerHTML=res;

        })

}
getUserPage();