// #### Gauge

// The Gauge object encapsulates the behavior
// of simple gauge. Most of the implementation
// is in the CSS rules, but we do have a bit
// of JavaScript to set or read the gauge value

function Gauge(el) {

    // ##### Private Properties and Attributes

    var element,      // Containing element for the info component
        data,         // `.gauge__data` element
        needle,       // `.gauge__needle` element
        background,   // `.gauge__background` element
        valueStr,     // `.gauge__label--value` element
        value = 0.0,  // Current gauge value from 0 to 1
        prop;         // Style for transform

    // ##### Private Methods and Functions

    var setElement = function(el) {
        // Keep a reference to the various elements and sub-elements
        element = el;
        data = element.querySelector(".gauge__data");
        needle = element.querySelector(".gauge__needle");
        background = element.querySelector(".gauge__background");
        valueStr = element.querySelector(".gauge__label--value");
    };

    var setValue = function(x) {
        value = x;
        var turns = -0.5 + (x * 0.5);
        data.style[prop] = "rotate(" + turns + "turn)";
        needle.style[prop] = "rotate(" + turns + "turn)";

        valueStr.innerHTML = (100 * value).toFixed(0);
        if ( value < 0.1 ) {
            data.style["background-color"] = "#ff0000"
            background.style["background-color"] = "#ffdfdf"
        } else if ( value < 0.3 ) {
            data.style["background-color"] = "#ff7f00"
            background.style["background-color"] = "#ffdfdf"
        } else {
            data.style["background-color"] = "#00ff00"
            background.style["background-color"] = "#dfffdf"
        }
    };

    // ##### Object to be Returned

    function exports() { };

    // ##### Public API Methods

    exports.element = function(el) {
        if (!arguments.length) { return element; }
        setElement(el);
        return this;
    };

    exports.value = function(x) {
        if (!arguments.length) { return value; }
        setValue(x);
        return this;
    };

    // ##### Initialization

    var body = document.getElementsByTagName("body")[0];
    ["webkitTransform", "mozTransform", "msTransform", "oTransform", "transform"].
        forEach(function(p) {
            if (typeof body.style[p] !== "undefined") { prop = p; }
        }
    );

    if (arguments.length) {
        setElement(el);
    }

    return exports;

};
