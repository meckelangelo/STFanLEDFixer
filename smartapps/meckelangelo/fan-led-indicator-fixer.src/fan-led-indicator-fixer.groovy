definition(
    name: "Fan LED Indicator Fixer",
    namespace: "meckelangelo",
    author: "David Meck",
    description: "Changes the indicator light setting to always be off.",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience%402x.png"
)

preferences {
    section("When this switch is toggled, adjust the indicator light...") { 
        input "main", "capability.switch", 
            multiple: false, 
            title: "Switch", 
            required: false
    }

    section("When this switch is toggled, adjust it in reverse... Change this setting only if the light is behaving opposite of expectations.") { 
        input "reverse", "bool", 
            title: "ON = Reversed, OFF = Normal",
            required: true,
            defaultValue: false
    }
}

def installed() {
    subscribe(main, "switch.on", switchOnHandler)
    subscribe(main, "switch.off", switchOffHandler)
}

def updated()
{
    unsubscribe()
    installed()
}

def switchOffHandler(evt) {
	if (!reverse) {
        main?.indicatorWhenOn()
    } else {
        main?.indicatorWhenOff()
    }
}

def switchOnHandler(evt) {
	if (!reverse) {
        main?.indicatorWhenOff()
    } else {
        main?.indicatorWhenOn()
    }
}