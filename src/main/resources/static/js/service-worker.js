// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here. Other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
firebase.initializeApp({
    apiKey: "AIzaSyBRNF1gHpyjMoMHjKIlSwfYGfiZOYdSZeE",
    authDomain: "leejeonmoon-835a7.firebaseapp.com",
    projectId: "leejeonmoon-835a7",
    storageBucket: "leejeonmoon-835a7.appspot.com",
    messagingSenderId: "947922006468",
    appId: "1:947922006468:web:221333ef2760165742b491",
    measurementId: "G-37JVR4H4QX"
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();