import axios from 'axios';

var AutobuskaStanica = axios.create({
    baseURL: 'http://localhost:8080/api',
    /* other custom settings */
  });

  AutobuskaStanica.interceptors.request.use(
    function success(config){
        const token = window.localStorage['jwt'];
        if (token){
          config.headers['Authorization'] = 'Bearer ' + token;
        }
        return config;
      }
  );

  export default AutobuskaStanica;