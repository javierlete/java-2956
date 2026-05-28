import { useState } from "react";

import styles from './Tarea.module.css';

export default function Tarea({ texto }) {
    return <label className={styles.tarea}>
        <input type="checkbox" />
        {texto}
    </label>;
}
