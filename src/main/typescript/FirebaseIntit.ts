// src/main/typescript/FirebaseInit.ts

import firebase from "firebase/app";
import "firebase/messaging";

export async function getToken() {
    if (firebase.messaging.isSupported() === false) {
        console.log("isSupported: ", firebase.messaging.isSupported());
        return null;
    }

    const messaging = firebase.messaging();
    const token = await messaging.requestPermission()
        .then(function () {
            return messaging.getToken();
        })
        .then(function (token) {
            return token;
        })
        .catch(function (err) {
            console.debug('에러 : ', err);
            return null;
        });

    console.log("token: ", token);
    return token;
}
