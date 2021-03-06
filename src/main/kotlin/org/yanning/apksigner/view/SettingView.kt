package org.yanning.apksigner.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.stage.FileChooser
import org.yanning.apksigner.app.Styles
import sun.awt.OSInfo
import tornadofx.*

class SettingView : View("Setting") {
    val signToolFieName = if (OSInfo.OSType.WINDOWS == OSInfo.getOSType()) "apksigner.exe" else "apksigner"
    val signToolPath = SimpleStringProperty()
    override val root = form {
        fieldset("Environment") {
            field(signToolFieName) {
                textfield(signToolPath) {
                    style {
                        minWidth = 200.px
                    }
                }
                setOnDragDropped {
                    it.dragboard.files?.first()?.let {
                        if (it.isFile && it.name.equals(signToolFieName))
                            signToolPath.set(it.absolutePath)
                    }
                }
                button("choose") {
                    setOnAction {
                        val apkFile = chooseFile("signTool picker",
                                arrayOf(FileChooser.ExtensionFilter(signToolFieName, signToolFieName)),
                                FileChooserMode.Single, currentWindow)
                        signToolPath.set(apkFile.firstOrNull()?.absolutePath)
                    }
                    style {
                        minWidth = 80.px
                    }
                }
            }
            field {
                hbox {
                    button("save") {
                        action {
                            tooltip("save success!") {

                            }
                        }
                        style {
                            minWidth = 120.px
                        }
                    }
                    style {
                        alignment = Pos.CENTER_RIGHT
                    }
                }
            }
        }

        style {
            Styles.getFontFamily()?.apply {
                fontFamily = this
            }
        }
    }
}
