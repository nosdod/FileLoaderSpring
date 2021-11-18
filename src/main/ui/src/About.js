import React from 'react';
import Modal from 'react-modal';
import Settings from './settings.json';

const customStyles = {
  content: {
    top: '50%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
  },
};

// Make sure to bind modal to your appElement (https://reactcommunity.org/react-modal/accessibility/)
Modal.setAppElement('#root');

export default function About(props) {
  let subtitle;

  function afterOpenModal() {
    // references are now sync'd and can be accessed.
    subtitle.style.color = '#f00';
  }

  function closeModal() {
    props.closeAbout();
  }

  return (
    <Modal
        isOpen={props.showAbout}
        onAfterOpen={afterOpenModal}
        onRequestClose={closeModal}
        style={customStyles}
        contentLabel="About Entropy Manager"
    >
        <h2 ref={(_subtitle) => (subtitle = _subtitle)}>About</h2>

        <h3>URL {Settings.serviceUrl}</h3>
        <button onClick={closeModal}>close</button>
        <div>Entropy Manager</div>
    </Modal>
  );
}

