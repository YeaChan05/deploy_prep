import FetchTemplate from "./FetchTemplate"
import { refreshToken } from "./RefreshToken";

const url = "http://localhost:8080";

export async function Login(id, password) {
    try {
        const response = await FetchTemplate({
            path: url + '/user/login',
            method: 'POST',
            body: JSON.stringify({
                'userId': id,
                'userPassword': password
            })
        });

        const result = await response.json();

        if (result.state === "FAIL") {
            alert(result.message);
            return;
        }

        if (result.state === "SUCCESS") {
            alert(result.message);
            return result.data;
        }
    } catch (error) {
        console.log(error);
    }
}

export async function Signup(id, password, name, nickname) {
    try {
        const response = await FetchTemplate({
            path: url + '/user',
            method: 'POST',
            body: JSON.stringify({
                'userId': id,
                'userPassword': password,
                'userName': name,
                'userNickname': nickname
            })
        });

        const result = await response.json();

        if (result.state === "FAIL") {
            alert(result.message);
        }

        if (result.state === "SUCCESS") {
            alert(result.message);
        }
    } catch (error) {
        console.log(error);
    }
}

export async function CheckId(id) {
    try {
        const response = await FetchTemplate({
            path: url + '/user/check/' + id,
            method: 'GET'
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
}

export async function myInfo(user, dispatch) {
    try {
        if (!user.refreshToken) {
            return;
        }

        let token = "";

        if (user.accessToken) {
            token = await refreshToken(dispatch, user);
        }

        const response = await FetchTemplate({
            path: url + '/user',
            method: 'GET',
            needToken: true,
            token: token
        });

        const result = await response.json();

        return result.data;
    } catch (error) {
        console.log(error);
    }
}

export async function updateUser(id, password, name, nickname, user, dispatch) {
    try {
        const response = await FetchTemplate({
            path: url + '/user',
            method: 'PUT',
            needToken: true,
            token: user.accessToken,
            body: JSON.stringify({
                "userId": id,
                "userPassword": password,
                "userName": name,
                "userNickname": nickname
            })
        });

        const result = await response.json();

        if (result.state === "SUCCESS") {
            alert(result.message);
            return;
        }

        const token = await refreshToken(dispatch, user);

        const responseRefresh = await FetchTemplate({
            path: url + '/user',
            method: 'PUT',
            needToken: true,
            token: token,
            body: JSON.stringify({
                "userId": id,
                "userPassword": password,
                "userName": name,
                "userNickname": nickname
            })
        });

        const resultRefresh = await responseRefresh.json();

        alert(resultRefresh.message);
        return;
    } catch (error) {
        console.log(error);
    }
}

export async function deleteUser(user, dispatch) {
    try {
        let token = "";

        if (user.accessToken) {
            token = await refreshToken(dispatch, user);
        }

        console.log(token);

        const response = await FetchTemplate({
            path: url + '/user',
            method: 'DELETE',
            needToken: true,
            token: token
        });

        const result = await response.json();

        console.log(result);

        alert(result.message);
        return;
    } catch (error) {
        console.log(error);
    }
}

export async function findPassword(id, name, email, user, dispatch) {
    try {
        const response = await FetchTemplate({
            path: url + '/user/findPw',
            method: 'POST',
            body: JSON.stringify({
                "userId": id,
                "userName": name
            })
        });

        const result = await response.json();

        if (result.state === "FAIL") {
            alert(result.message);
            return;
        }

        const responseEmail = await FetchTemplate({
            path: url + '/user/findPw/' + result.data,
            method: 'POST',
            body: JSON.stringify({
                "email": email
            })
        });

        const resultEmail = await responseEmail.json();

        alert(resultEmail.message);
        return;
    } catch (error) {
        console.log(error);
    }
}