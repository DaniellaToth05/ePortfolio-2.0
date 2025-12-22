<script>
    let {
      open = false,
      symbol = "",
      currentPrice = 0,
      onClose,
      onUpdated
    } = $props();
  
    let price = $state(0);
    $effect(() => {
      if (open) {
        price = currentPrice;
      }
    });
  
    async function confirm() {
      if (!price){
        return;
      } 
  
      await fetch("http://localhost:8080/api/update-price", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          symbol,
          price: Number(price)
        })
      });
  
      onUpdated();
      onClose();
    }
  </script>
  
  {#if open}
    <dialog open class="modal">
      <h3>Update {symbol}</h3>
  
      <input
        type="number"
        step="0.01"
        placeholder="New price"
        bind:value={price}
      />
  
      <div class="actions">
        <button type="button" onclick={confirm}>Update</button>
  
        <form method="dialog">
          <button
            type="submit"
            class="secondary"
            onclick={onClose}
          >
            Cancel
          </button>
        </form>
      </div>
    </dialog>
  {/if}
  
  <style>
    dialog.modal {
      border: none;
      border-radius: 8px;
      padding: 1.5rem;
      width: 300px;
    }
  
    dialog::backdrop {
      background: rgba(0, 0, 0, 0.4);
    }
  
    .actions {
      display: flex;
      justify-content: flex-end;
      gap: 0.5rem;
    }
  
    .secondary {
      background: #eee;
    }
  </style>
  