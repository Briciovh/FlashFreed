/**
 * Import function triggers from their respective submodules:
 *
 * import {onCall} from "firebase-functions/v2/https";
 * import {onDocumentWritten} from "firebase-functions/v2/firestore";
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

import {setGlobalOptions} from "firebase-functions";
import {onRequest} from "firebase-functions/https";
import * as logger from "firebase-functions/logger";

// Start writing functions
// https://firebase.google.com/docs/functions/typescript

// For cost control, you can set the maximum number of containers that can be
// running at the same time. This helps mitigate the impact of unexpected
// traffic spikes by instead downgrading performance. This limit is a
// per-function limit. You can override the limit for each function using the
// `maxInstances` option in the function's options, e.g.
// `onRequest({ maxInstances: 5 }, (req, res) => { ... })`.
// NOTE: setGlobalOptions does not apply to functions using the v1 API. V1
// functions should each use functions.runWith({ maxInstances: 10 }) instead.
// In the v1 API, each function can only serve one request per container, so
// this will be the maximum concurrent request count.
setGlobalOptions({ maxInstances: 10 });

// export const helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });


import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

admin.initializeApp();

export const onLikeCreated = functions.firestore
  .document("posts/{postId}/likes/{uid}")
  .onCreate(async (snap, context) => {
    const { postId, uid: likerUid } = context.params;

    // Obtener el post para saber quién es el autor
    const postSnap = await admin.firestore()
      .doc(`posts/${postId}`).get();
    const post = postSnap.data();
    if (!post) return null;

    // No notificar si el autor se da like a sí mismo
    if (post.authorUid === likerUid) return null;

    // Obtener FCM token del autor
    const authorSnap = await admin.firestore()
      .doc(`users/${post.authorUid}`).get();
    const fcmToken = authorSnap.data()?.fcmToken;
    if (!fcmToken) return null;

    // Obtener nombre del usuario que dio like
    const likerSnap = await admin.firestore()
      .doc(`users/${likerUid}`).get();
    const likerName = likerSnap.data()?.displayName ?? "Alguien";

    // Enviar notificación
    return admin.messaging().send({
      token: fcmToken,
      notification: {
        title: "¡Nuevo like!",
        body: `${likerName} le dio like a tu post`,
      },
      data: { postId },
    });
  });
