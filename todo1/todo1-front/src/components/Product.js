import React from 'react';
import ItemList from './ItemList';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import axios from 'axios';
import Menu from './Menu';

class Product extends React.Component {
    state = {
        show: false,
        type: "",
        superhero: "",
        brand: "",
        price: 0,
        quantity: 0,
        saveDisabled: false,
        items: [] 
    }

    async componentDidMount () {
        const response =
        await axios.get("http://localhost:4567/shirts");
        console.log(response);

        this.setState({
            items: response.data
        });
    }

    handleClick = (e) => {
        this.setState({ 
            show: true 
          });
      
          if (e) {
            e.preventDefault();
          }
    }

    handleClose = (e) => {
        this.setState({
            show: false
        });
        
        if (e) {
            e.preventDefault();
        }
    }

    handleInput(event, key) {
        this.setState({
          [key]: event.target.value
        });
      }

    handleSave = async (e) => {
        let itemToSave = {
            type: this.state.type,
            superhero: this.state.superhero,
            brand: this.state.brand,
            price: this.state.price,
            quantity: this.state.quantity,
        };
        
        await axios.post("http://localhost:4567/shirts", itemToSave, {
            headers: {
              "Content-Type": "application/json"
            }
          });
        let newItems = this.state.items;
        newItems.push(itemToSave);
        this.setState({
            items: newItems
        });
        this.setState({
            show: false
        });
        if (e) {
            e.preventDefault();
        }
    }

    

    render() {
        return (
            <div>
                <Menu history={this.props.history}/>
                <button className="btn btn-info" onClick={this.handleClick}>Agregar</button>
                <ItemList items={this.state.items}/>
                <Modal show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Agregar Remera</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div>Tipo de Producto <input type="text" onChange={event => this.handleInput(event, 'type')}/></div>
                        <div>Superheroe <input type="text" onChange={event => this.handleInput(event, 'superhero')}/></div>
                        <div>Marca <input type="text" onChange={event => this.handleInput(event, 'brand')}/></div>
                        <div>Cantidad en Stock <input type="text" onChange={event => this.handleInput(event, 'quantity')}/></div>
                        <div>Precio <input type="text" onChange={event => this.handleInput(event, 'price')}/></div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.handleClose}>
                        Cancelar
                        </Button>
                        <Button variant="primary" onClick={this.handleSave} disabled={this.state.saveDisabled}>
                        Guardar
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>

        )
    }
}

export default Product;


