import Mensaje from "./Mensaje";

export default function ListaMensajes() {
    return <ul class="list-group">
        <li class="list-group-item d-flex justify-content-between align-items-start"><Mensaje/></li>
        <li class="list-group-item d-flex justify-content-between align-items-start"><Mensaje/></li>
        <li class="list-group-item d-flex justify-content-between align-items-start"><Mensaje/></li>
        <li class="list-group-item d-flex justify-content-between align-items-start"><Mensaje/></li>
        <li class="list-group-item d-flex justify-content-between align-items-start"><Mensaje/></li>
    </ul>;
}