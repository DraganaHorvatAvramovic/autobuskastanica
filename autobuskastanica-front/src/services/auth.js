import AutobuskaStanicaAxios from "../apis/AutobuskaStanicaAxios"

export const login = async function(username, password){
    const data = {
        username: username,
        password: password
    }
    try{
        const ret = await AutobuskaStanicaAxios.post('korisnici/auth', data);
        window.localStorage.setItem('jwt', ret.data);
    }catch(error){
        console.log(error);
    }
    window.location.reload();
}

export const logout = function(){
    window.localStorage.removeItem('jwt');
    window.location.reload();
}