import React from 'react';
import AutobuskaStanicaAxios from './../../apis/AutobuskaStanicaAxios';
import {Button, Form, Table} from "react-bootstrap";

class AutobuskaStanica extends React.Component {

    constructor(props){
        super(props);

        this.state = { 
            linije: [],
            prevoznici: [],
            search: {destinacija: "", prevoznikId: -1, cena_karte: 0.00},
            pageNo: 0,
            totalPages: 0
        }
    }

    componentDidMount(){
        this.getLinije();
        this.getPrevoznici();
        
    }

    async getPrevoznici(){
        try{
            let result = await AutobuskaStanicaAxios.get("/prevoznici");
            let prevoznici = result.data;
            this.setState({prevoznici: prevoznici});
            console.log("test1");
        }catch(error){
            console.log(error);
            alert("Greska pri pribavljanju prevoznika");
        }
    }

    getLinije(){
        let config = {
            params: {
                pageNo: this.state.pageNo
            },
        }
        if(this.state.search.destinacija != ""){
            config.params.destinacija = this.state.search.destinacija;
        }
        if(this.state.search.prevoznikId != -1){
            config.params.prevoznikId = this.state.search.prevoznikId;
        }
        if(this.state.search.cena_karte != 0.00){
            config.params.cena_karte = this.state.search.cena_karte;
        }
        AutobuskaStanicaAxios.get('/linije', config)
        .then(res => {
            // handle success
            console.log(res);
            this.setState({
                linije: res.data, 
                totalPages: res.headers["total-pages"]
             });
       })
       .catch(error => {
           // handle error
           console.log(error);
           alert('Error occured please try again!');
       });
    }

    goToAdd(){
        this.props.history.push('/linije/add'); 
    }
    goToEdit(linijaId){
        this.props.history.push('/linije/edit/'+ linijaId); 
    }

    goToRezervacija(linijaId){
        this.props.history.push('/linije/rezervacija/'+ linijaId); 
    }
   
    
    
    
    renderLinije(){
       return this.state.linije.map((linija) => {
            return(
                <tr key={linija.id}>
                    <td>{linija.brroj_mesta}</td>
                    <td>{linija.cena_karte}</td>
                    <td>{linija.vreme_polska}</td>
                    <td>{linija.destinacija}</td>
                    <td>{linija.prevoznikDTO.naziv}</td>
                    <td><Button variant="danger" onClick={() => this.goToRezervacija(linija.id)}>Rezervisi</Button></td>
                    <td><Button variant="warning" onClick={() => this.goToEdit(linija.id)}>Edit</Button></td>
                  <td><Button variant="danger" onClick={() => this.delete(linija.id)}>Delete</Button></td>
                </tr>
            )
        })
    }
    doSearch(){
        this.getLinije();
    }

    searchValueInputChange(event) {
        let control = event.target;
    
        let name = control.name;
        let value = control.value;
    
        let search = this.state.search;
        search[name] = value;
    
        this.setState({ search: search });
      }

    render(){
        return (
            <div>
                <h1>Linije</h1>
                <div>
                    <Button onClick={() => this.goToAdd() }>Dodaj</Button>
                    <br/>
                    <Form style={{marginTop:35}}>
                        <Form.Group>
                            <Form.Label>Destinacija</Form.Label>
                                <Form.Control name="destinacija" as="input" value={this.state.search.destinacija} onChange={(e) => this.searchValueInputChange(e)}>
                                </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Prevoznik</Form.Label>
                                <Form.Control name="prevoznikId" as="select" value={this.state.search.prevoznikId}  onChange={(e) => this.searchValueInputChange(e)}>
                                    <option value={-1}></option>
                                        {this.state.prevoznici.map((prevoznik) => {
                                     return (
                                    <option value={prevoznik.id} key={prevoznik.id}>
                                        {prevoznik.naziv}
                                     </option>
                                 );
                                 })}
                                </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Maksimalna cena</Form.Label>
                                <Form.Control name="cena_karte" as="input" value={this.state.search.cena_karte} onChange={(e) => this.searchValueInputChange(e)}>
                            </Form.Control>
                        </Form.Group>
                        <Button onClick={() => this.doSearch()}>Pretraga</Button>
                    </Form>
                    <Table style={{marginTop:5}}>
                        <thead>
                            <tr>
                                <th>Broj mesta</th>
                                <th>Cena karte</th>
                                <th>Vreme polaska</th>
                                <th>Destinacija</th>
                                <th>Prevoznik</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.renderLinije()}
                        </tbody>
                    </Table>
                    <div><Button disabled={this.state.pageNo==0} onClick={() => this.getLinije(this.state.pageNo= this.state.pageNo-1)} >Previous</Button>
                    <Button disabled={this.state.pageNo==this.state.totalPages-1} onClick={() =>  this.getLinije(this.state.pageNo= this.state.pageNo+1)}>Next</Button></div>
                </div>
            </div>
        );

    }
}

export default AutobuskaStanica;