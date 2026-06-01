import { useState } from "react";

import styles from './Tarea.module.css';

export default function Tarea({ tarea }) {
    return <label className={styles.tarea}>
        <input type="checkbox" checked={tarea.terminada} />
        {tarea.texto}
    </label>;
}
