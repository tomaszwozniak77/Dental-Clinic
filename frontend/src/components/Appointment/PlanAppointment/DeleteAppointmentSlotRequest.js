import Cookies from "js-cookie";
import axios from "axios";
import successAlerts from "../../Alerts/SuccessAlerts/SuccessAlerts";
import errorAlerts from "../../Alerts/ErrorAlerts/ErrorAlerts";

export function removeAppointmentSlotRequest(id, render, t) {
    let token = Cookies.get(process.env.REACT_APP_JWT_TOKEN_COOKIE_NAME);
    let configDeleteAppointmentSlot = {
        method: 'put',
        url: process.env.REACT_APP_BACKEND_URL + "appointment/slot/delete",
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json',
        },
        data: {
            id: id
        }
    };

    axios(configDeleteAppointmentSlot, t)
        .then(function (response) {
            successAlerts(t(response.data.message, response.status)).then(() => {})
            render();
        })
        .catch((response) => {
            console.log(response);
            if (response.response) {
                errorAlerts(t(response.response.data.message), response.response.status.toString(10));
            }
        });
}