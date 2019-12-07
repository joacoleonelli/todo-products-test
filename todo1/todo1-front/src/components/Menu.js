import React from "react";
import MenuButton from "./MenuButton";

class Menu extends React.Component {
    render() {
        return (
            <div className="header-menu">
            {console.log(this.props)}
                <MenuButton history={this.props.history} path={"inicio"} title={'Inicio'} />
                <MenuButton history={this.props.history} path={"products"} title={'Productos'} />
            </div>            
        );
    }
}

export default Menu;