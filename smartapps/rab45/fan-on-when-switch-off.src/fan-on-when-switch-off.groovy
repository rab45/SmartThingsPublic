/**
 *  Fan on when switch off
 *
 *  Copyright 2017 Robby Bloome
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Fan on when switch off",
    namespace: "rab45",
    author: "Robby Bloome",
    description: "When switch is turned off, waits 30 seconds, turns switch back on and turns off smart lights, allowing fan to stay on",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("When switch is turned off...") {
		input "theswitch","capability.switch",multiple:true
	}
    section("Turn on..."){
    	input "switches","capability.switch",multiple:true
    }
    section("turn lights off..."){
    	input "lights","capability.switch",multiple:true
    }
    
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(theswitch, "switch.off", switchoffhandler)
}

def switchoffhandler(evt){
	switches.on([delay:30000])
    lights.off([delay:30000])
}
	