import React from 'react';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import axios from 'axios';

class ItemList extends React.Component {
    state = {
        show: false,
        id: null,
        superhero: "",
        brand: "",
        price: 0,
        quantity: 0,
        saveDisabled: false,
        items: [] 
    }

    edit = (data) => {
        this.setState({
            show: true,
            id: data.id,
            type:data.type,
            superhero: data.superhero,
            brand: data.brand,
            price: data.price,
            quantity: data.quantity
          });
    }

    delete = async (data) => {
        await axios.delete(`http://localhost:4567/shirts/${data.id}`);
        window.location.reload(); 
    }

    sellProduct = async (data) => {
        const response = await axios.put(`http://localhost:4567/shirts/${data.id}?operation=sell`);
        if (response.data.status === 400 && response.data.message === "No hay mas stock del producto.") {
            alert("No hay más stock de este producto. Por favor reponga stock");
        } else {
            window.location.reload();
        }
    }

    addStock = async (data) => {
        await axios.put(`http://localhost:4567/shirts/${data.id}?operation=addStock`);
        window.location.reload();
    }

    createTable = () => {
        const { items } = this.props;
        let table = []
        let body = [];
        table.push(<thead>
            <tr>
            <th>Tipo de Producto</th>   
            <th>Superheroe</th>
            <th>Marca</th>
            <th>Cantidad en Stock</th>
            <th>Precio</th>
            <th>Acciones</th>
            </tr>
        </thead>);

        items.forEach(item => {
            let tr = [];
            let td = [];
            td.push(<td>{item.type}</td>)
            td.push(<td>{item.superhero}</td>)
            td.push(<td>{item.brand}</td>)
            td.push(<td>{item.quantity}</td>)
            td.push(<td>{item.price}</td>)
            td.push(<td><button className="btn btn-info" onClick={() => this.edit(item)}>Editar</button>
            <button className="btn btn-info" onClick={() => this.sellProduct(item)}>Vender</button>
            <button className="btn btn-info" onClick={() => this.addStock(item)}>Agregar Stock</button>
            <button className="btn btn-info"onClick={() => this.delete(item)}>Borrar</button></td>)
            tr.push(<tr>{td}</tr>);
            body.push(tr);
        });

        table.push(<tbody>{body}</tbody>);

        return table
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
        
        await axios.put(`http://localhost:4567/shirts/${this.state.id}`, itemToSave, {
            headers: {
              "Content-Type": "application/json"
            }
          });
        this.setState({
            show: false
        });

        window.location.reload(); 
    }

    render() {
        return (
            <div>
            <Table striped bordered hover>
                {this.createTable()}
            </Table>
            <Modal show={this.state.show} onHide={this.handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Editar Producto</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div>Tipo de Producto <input type="text" value={this.state.type} onChange={event => this.handleInput(event, 'type')}/></div>
                <div>Superheroe <input type="text" value={this.state.superhero} onChange={event => this.handleInput(event, 'superhero')}/></div>
                <div>Marca <input type="text" value={this.state.brand} onChange={event => this.handleInput(event, 'brand')}/></div>
                <div>Cantidad en Stock <input type="text" value={this.state.quantity} onChange={event => this.handleInput(event, 'quantity')}/></div>
                <div>Precio <input type="text" value={this.state.price} onChange={event => this.handleInput(event, 'price')}/></div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={this.handleClose}>
                Cancelar
                </Button>
                <Button variant="primary" onClick={this.handleSave} disabled={this.state.saveDisabled}>
                Actualizar
                </Button>
            </Modal.Footer>
        </Modal>
        </div>
        )
    }
}

export default ItemList;


