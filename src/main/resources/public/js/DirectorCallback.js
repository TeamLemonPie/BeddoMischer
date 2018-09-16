/*
Handles all director callback events from server.
 */

let animation;

function handleDirectorCallback(command, key, value) {
    if (command === "lower_third_start") {

        if (animation != null) {
            connection.send(JSON.stringify({
                scope: "DIRECTOR",
                command: "lower_third_failed",
                key: key
            }));
            return;
        }

        animation = bodymovin.loadAnimation({
            container: document.getElementById("animation"),
            renderer: "svg",
            path: "/lowerthird/" + key,
            autoplay: false
        });

        if (isBoardVisible()) {
            animation.addEventListener('complete', function () {
                animation.destroy();
                animation = null;
            });

            fullAnimation(animation, key);
        } else {
            animation.addEventListener('complete', function () {
                animation.destroy();
                animation = null;

                connection.send(JSON.stringify({
                    scope: "DIRECTOR",
                    command: "lower_third_finish",
                    key: key
                }));
            });

            animation.play();
        }
    }
}